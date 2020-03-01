.PHONY: run_mw
run_mw:
	cd $ZOOKEEPER_HOME/bin/
	zkServer.sh start 
	kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties


.PHONY: list_kafka_topic
list_kafka_topic:
	kafka-topics.sh --list --zookeeper localhost:2181

	
.PHONY: check_kafka_topic
check_kafka_topic:
	kafka-topics.sh --zookeeper localhost:2181 --describe --topic EMPLOYEE