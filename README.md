id-generator-java
=================

ID generator implemented by Java

Format:

**********************************************************************
* T |64| * L |6| *R |4|* N |12| * S |10| *
**********************************************************************

1. ID = TLRNS, 96 bits
2. T time stamp in ms, 64 bits
3. L logical regions, likes district or ISP, 6bits, capacity is 64 regions
4. R reserved bits, 4 bits, capacity 16, for future requirements.
5. N physical id generator nodes, 12 bits, capacity is 4096 nodes
6. S sequence number, there a sequence counter per node, 10bits, capacity 1024, it means every node can produce 1024000 IDs per second per node.

Use ObjectId as _id type in mongo DB.

<a href='http://riderzen.com/?x=entry:entry120517-022657'>ID Server 策略与实现</a>
