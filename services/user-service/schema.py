"""schema.py
This module defines the graphQL schema"""
import graphene
from graphene_sqlalchemy import SQLAlchemyObjectType
from models import Customer as CustomerModel
from database import db_session
from datetime import datetime


class Customer(SQLAlchemyObjectType):
    class Meta:
        model = CustomerModel

class CreateCustomer(graphene.Mutation):
    class Arguments:
        name = graphene.String(required=True)
        code = graphene.String(required=True)
        phone = graphene.String(required=True)

    customer = graphene.Field(lambda: Customer)

    def mutate(self, info, name, code, phone):
        customer = CustomerModel(
            name=name,
            code=code,
            phone=phone,
            created_at=datetime.utcnow()
        )
        db_session.add(customer)
        db_session.commit()
        return CreateCustomer(customer=customer)

class Query(graphene.ObjectType):
    customers = graphene.List(Customer)
    customer = graphene.Field(Customer, code=graphene.String())

    def resolve_customers(self, info):
        return CustomerModel.query.all()

    def resolve_customer(self, info, code):
        return CustomerModel.query.filter_by(code=code).first()

class Mutation(graphene.ObjectType):
    create_customer = CreateCustomer.Field()

schema = graphene.Schema(query=Query, mutation=Mutation)
