# Simple-User-Domain-CQRS-using-Axon-Framework

This simple project describe simple user domain cqrs using Axon Framework
there are 3 changes event in this sample, they are

UserCreatedEvent -> UserActivatedEvent -> UserDeactivatedEvent

# Design
---------------------------------
CommandUserService
(Using mongodb as event storage)
----------------------------------
                 |
                \/    push event to kafka

----------------------------
kafka
----------------------------
                 |
                \/    describe event for query persentation layer

--------------------------------------
QueryUserService
(using redis for persentation storage)
---------------------------------------