Lamdas, Stream API of Java 8

Sacalability using Java 9 Project Jigsaw
Java getting competition from Kotlin (mainly for Android development ), Scala, Groovy  - all jvm based languages

--> Scalability
Horizontal or Scaling out - Adding more nodes and redistributing load among them. SOA, microservice and web servers scale out by adding more servers.

Vertical Scaling or Scale up - Adding more computing or memory resources to the same servers. Virtulization.

--> High Availability
Degree of functional continuity with in time window specified.
A = 100 - (100*D/U) D - Unplanned Downtime U - Uptime expressed in minutes.

some standard metrics
90 oe one nine - 90% available or 36.5 days down.
99 or two nince - 99% available or 4 days down
99.9 or three nine - 99.9% available or 8.8 hours down.
99.99 or four nine - 99.99% available or 5.3 min down.
99.999 or five nine - 99.999% available or 32 seconds down.

--> Elasticity
Elasticity is ability to dynamically add or remove resources from the system based on demand. Specilized implementation of Scaling.

--> Scalability implementation rules
1. SLA to scale out or scale up?
2. trading system should scale in real time and within availabilty level.
3. Ecommerce system can scale in during slow months and scale out during holiday seasons.

--> Load Balancing
Techinique to minimise response time and increase throughput by spreading request on two or more resources. It could be implemented as hardware or software
Round robin, least connected, ip hashing etc can be used as algo.
-->Persistent Load Balancer - Stateful application require persistent or sticky load balancer. It require data sharing between nodes. This can be achieved using caching techniques.

