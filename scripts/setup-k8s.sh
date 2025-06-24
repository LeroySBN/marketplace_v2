#!/usr/bin/env bash

# colors
blue='\e[1;34m'
yellow='\e[1;33;1;40m'
green='\e[1;32m'
reset='\033[0m'

#####################
#####################
# STEP 1: Install kubelet kubeadm kubectl
echo -e "${blue}Installing Kubernetes...${reset}\n"

sudo apt-get update

# apt-transport-https may be a dummy package; if so, you can skip that package
sudo apt-get install -y apt-transport-https ca-certificates curl gpg

# If the directory `/etc/apt/keyrings` does not exist, it should be created before the curl command, read the note below.
# sudo mkdir -p -m 755 /etc/apt/keyrings
curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.33/deb/Release.key | \
  sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg

# This overwrites any existing configuration in /etc/apt/sources.list.d/kubernetes.list
echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.33/deb/ /' | \
  sudo tee /etc/apt/sources.list.d/kubernetes.list

sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
sudo apt-mark hold kubelet kubeadm kubectl

sudo systemctl enable --now kubelet

#####################
#####################
# STEP 2: Install a Container Runtime (containerd)
echo -e "${blue}Installing${reset}${yellow} containerd ${reset}${blue}for Kubernetes CRI...${reset}\n"

# Add Docker's official GPG key:
#sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo systemctl start containerd
sudo systemctl enable containerd

echo -e "${yellow}Configuring containerd...${reset}\n"
sudo mkdir -p /etc/containerd
sudo containerd config default | sudo tee /etc/containerd/config.toml

# Enable CRI and SystemdCgroup
sudo sed -i 's/disabled_plugins = \["cri"\]/disabled_plugins = []/g' /etc/containerd/config.toml
sudo sed -i 's/SystemdCgroup = false/SystemdCgroup = true/g' /etc/containerd/config.toml

sudo systemctl restart containerd

echo -e "${green}Containerd configured. Testing CRI...${reset}\n"
sudo crictl --runtime-endpoint unix:///var/run/containerd/containerd.sock version

#####################
#####################
# STEP 3: Initialize the K8s cluster
echo -e "${blue}Configuring firewall for Kubernetes with ${reset}${yellow}Calico...${reset}\n"

# Control plane ports
sudo ufw allow 6443/tcp # API Server
sudo ufw allow 2379:2380/tcp # etcd server client API
sudo ufw allow 10250/tcp # Kubelet API
sudo ufw allow 10259/tcp # kube-scheduler
sudo ufw allow 10257/tcp # kube-controller-manager

# Worker nodes ports
sudo ufw allow 10256/tcp # kube-proxy
sudo ufw allow 30000:32767/tcp # NodePort Services (if using NodePort)

# Calico-specific ports
sudo ufw allow 179/tcp     # BGP mode
#sudo ufw allow 4789/udp    # VXLAN mode (if having networking issues)
#sudo ufw allow 5473/tcp    # Calico Typha (for large clusters)
echo -e "${green}Firewall configured for Kubernetes with Calico BGP mode...${reset}\n"

echo -e "${blue}Initializing cluster with Calico CIDR...${reset}\n"
sudo kubeadm init --pod-network-cidr=192.168.0.0/16 > kubeadm_join_cmd
echo -e "${blue}Use the ${yellow}kubeadm join command${reset}${yellow} saved in the file ${yellow}kubeadm_join_cmd${reset}${yellow}\n"

echo -e "${blue}Configuring kubectl access for regular user...${reset}\n"
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

echo -e "${blue}Installing Calico CNI...${reset}\n"
kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.30.2/manifests/tigera-operator.yaml # Install Tigera Calico operator
kubectl create -f https://raw.githubusercontent.com/projectcalico/calico/v3.30.2/manifests/custom-resources.yaml
#curl https://raw.githubusercontent.com/projectcalico/calico/v3.30.2/manifests/custom-resources.yaml -O # Download and modify the custom resources
#sed -i 's|192.168.0.0/16|192.168.0.0/16|g' custom-resources.yaml # Edit the CIDR to match your kubeadm init
#kubectl create -f custom-resources.yaml # Apply the configuration

echo -e "${yellow}Verifying Calico installation...${reset}\n"
#kubectl get pods -n calico-system --watch # Watch Calico pods start up
kubectl get nodes # Check node status
kubectl get pods -n calico-system # Verify Calico is managing networking

# If this is a single-node cluster, remove taint that prevents pods from being scheduled on the control plane
kubectl taint nodes --all node-role.kubernetes.io/control-plane-

#####################
#####################
# STEP 4: Install HELM
echo -e "${blue}Almost done.${reset}\n\nStarting ${yellow}HELM${reset}${blue} installation...${reset}\n"

curl https://baltocdn.com/helm/signing.asc | gpg --dearmor | sudo tee /usr/share/keyrings/helm.gpg > /dev/null
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/helm.gpg] https://baltocdn.com/helm/stable/debian/ all main" | sudo tee /etc/apt/sources.list.d/helm-stable-debian.list
sudo apt-get update
sudo apt-get install helm

helm version

echo -e"${green}HELM installation completed.${reset}\n\n${blue}DONE${reset}"

