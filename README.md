# Android Clean Architecture
<br /> 

[![N|Solid](https://lh3.googleusercontent.com/guADWYsx75_ldKlBPP7w-svEs2kLoIgHdoxemnzeXnoOjmBMrcX2aA9mj5MdssnX4v6xZMkAMQz019i0i47pKdo9HsKluHf3wvvTA8E87gEuVE4prBWwVKxpY14YaPtnDHzUt244l0msyUA7XeylyZPynXN8txVT9Kk9KGXcGcGKTHQfHdyiqO0eM3DeY2DRma5j4ADE7AJsfRwbJgzWPqLGxyQd3SITOWCetgv9CNIV7SyhTi7upGhdV4Dht1GDnVQYmb4jduHV6kLDs1pQOtkV5owMyt9JwUGEA0f-hga5aZxaaGfdP4fdlGum6qqyfhxoRQvFKWBaJ1c1tcKZ2uhByTg7LkJFsn8bzZjAK3kdM6nWsVPWfwLD9NprfjohrOcVM0pXXhiXovWvRwd4ug4I98HrtJMp3bAn97AuRQhH5W12pLR1wgbKxkCnGWAzcglfnYbSPMq-ykt-JkPJJ75W1KIjtSx_CRool1jX6JEXz7znADmVVfIeXrty4OvnqGzXDeYWekpZlZBuRNPkzgrIt5bAVy_BkAyAeRwQrv4UsOms6OxSoiBB06f8E8Jvn_l0-Ca3L8K61FMCQl1cuOHhtbK-QCC48cdLrp5tdyIm6uuhUiCU5IYSoKZwmckR7crq5KvKmWuN60q0OxNt7gPV_eZEh1Q=w772-h567-no)

## Simple fetch and display of data using Clean Architecture approach.

In short, cleanly architecture code should be separated into multiple concentretic layers, taking the form of an onion shape, with the primary goal of the separation of concerns. To acheive this, the overriding rule that makes this architecture work is **The Dependency Rule.** This rule states that source code dependencies must only point inwards. Nothing in an inner circle can know anything at all about something in an outer circle.

> The inner layers should not know anything about the outer layers. Meaning that the dependencies should point inwards.

When correctly implemented, the benefits include:

- **Independent of Frameworks.** The architecture does not depend on the existence of some library of feature laden software. This allows you to use such frameworks as tools, rather than having to cram your system into their limited constraints.
- **Testable.** The business rules can be tested without the UI, Database, Web Server, or any other external element.
- **Independent of UI.** The UI can change easily, without changing the rest of the system. 
- **Independent of Database.** Your business rules are not bound to the database.
- **Independent of any external agency.** Business rules simply don’t know anything at all about the outside world.

### The Dependency Rule
The concentric circles represent different areas of software. In general, the further in you go, the higher level the software becomes. The outer circles are mechanisms. The inner circles are policies. data formats used in an outer circle should not be used by an inner circle, especially if those formats are generate by a framework in an outer circle. We don’t want anything in an outer circle to impact the inner circles.

## Layers

**Entities**
Entities are the business objects of the application. They encapsulate the most general and high-level rules. They are the least likely to change when something external changes.

**Use Cases**
The software in this layer contains application specific business rules. It encapsulates and implements all of the use cases of the system. These use cases orchestrate the flow of data to and from the entities, and direct those entities to use the business rules to achieve the goals of the use case.

>We don't expect:  changes in this layer to affect the entities. We also do not expect this layer to be affected by changes to externalities such as the database, the UI, or any of the common frameworks. This layer is isolated from such concerns.

>We do expect -> that changes to the operation of the application will affect the use-cases and therefore the software in this layer. 

**Interface Mappers**
The software in this layer is a set of adapters that convert data from the format most convenient for the use cases and entities, to the format most convenient for some external agency such as the Database or the UI. It is this layer, for example, that will wholly contain the architecture of a GUI. The Presenters, Views, and Controllers all belong in here. The models are likely just data structures that are passed from the controllers to the use cases, and then back from the use cases to the presenters and views.

Similarly, data is converted, in this layer, from the form most convenient for entities and use cases, into the form most convenient for whatever persistence framework is being used. i.e. The Database. No code inward of this circle should know anything at all about the database.

**Frameworks and Drivers**
The outermost layer is generally composed of frameworks and tools. Generally you don’t write much code in this layer other than glue code that communicates to the next circle inwards.

**Conclusion**
By separating the software into layers, and conforming to The Dependency Rule, you will create a system that is intrinsically testable, with all the benefits that implies. When any of the external parts of the system become obsolete, like the database, or the web framework, you can replace those obsolete elements with a minimum of fuss.

**The Clean Code Blog, by Robert C. Martin (Uncle Bob)**
| [https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html][PlDb] |

