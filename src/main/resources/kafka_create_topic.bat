cd\
cd C:\Manu\Tools\kafka_2.11-0.11.0.0\bin\windows
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TextLinesTopic
