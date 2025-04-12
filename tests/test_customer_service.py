import pytest
from services.customer_service.app import app
from services.customer_service.database import db_session, init_db
from services.customer_service.models import Customer

@pytest.fixture
def client():
    app.config['TESTING'] = True
    with app.test_client() as client:
        init_db()
        yield client
        db_session.remove()

def test_health_check(client):
    rv = client.get('/health')
    assert rv.status_code == 200
    assert rv.json['status'] == 'healthy'

def test_create_customer():
    mutation = '''
    mutation {
        createCustomer(name: "Test User", code: "TEST001", phone: "+254700000000") {
            customer {
                id
                name
                code
                phone
            }
        }
    }
    '''
    # Note: Actual GraphQL test would need proper authentication setup
