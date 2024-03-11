For the Tracker application

***Some Kafka commands to keep in mind***

* brew install kafka
* zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties - zookeeper start
* kafka-server-start /usr/local/etc/kafka/server.properties - kafka start
* kafka-console-producer --broker-list localhost:9092 --topic first-topic - to produce data
* kafka-console-consumer --bootstrap-server localhost:9092 --topic first-topic - Listen to consume data
* kafka-topics --list --bootstrap-server localhost:9092 - list all Kafka topics
*  kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic first-topic - create topic
* kafka-topics --describe --bootstrap-server localhost:9092 --topic first-topic - describe topics

