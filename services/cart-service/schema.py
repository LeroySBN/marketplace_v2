import graphene
from graphene_sqlalchemy import SQLAlchemyObjectType
from models import Order as OrderModel
from database import db_session
from datetime import datetime
from app import publish_order

class Order(SQLAlchemyObjectType):
    class Meta:
        model = OrderModel

class CreateOrder(graphene.Mutation):
    class Arguments:
        customer_code = graphene.String(required=True)
        item = graphene.String(required=True)
        amount = graphene.Float(required=True)

    order = graphene.Field(lambda: Order)

    def mutate(self, info, customer_code, item, amount):
        order = OrderModel(
            customer_code=customer_code,
            item=item,
            amount=amount,
            created_at=datetime.utcnow()
        )
        db_session.add(order)
        db_session.commit()

        # Publish order to RabbitMQ for notification
        publish_order({
            'customer_code': customer_code,
            'item': item,
            'amount': amount,
            'created_at': order.created_at.isoformat()
        })

        return CreateOrder(order=order)

class Query(graphene.ObjectType):
    orders = graphene.List(Order)
    order = graphene.Field(Order, id=graphene.Int())

    def resolve_orders(self, info):
        return OrderModel.query.all()

    def resolve_order(self, info, id):
        return OrderModel.query.get(id)

class Mutation(graphene.ObjectType):
    create_order = CreateOrder.Field()

schema = graphene.Schema(query=Query, mutation=Mutation)
