# twitter-kafka-spark

## This project is about setting up kafka and spark for streaming twitter feeds.

## it contains streaming the twitter feeds, kafka, and spark independently, and linked via main. 

in order to run the twitter stream, a twitter developer account is required.
From the developer account, you need to have an app created with the app. 
Information on how to create an app with twitter can be found at: <br />
https://docs.inboundnow.com/guide/create-twitter-application/

running this project for Kafka and Spark requires to have zookeeper and kafka servers running. <br />
Below are the steps to run those.

1. cd into your kafka directory<br />
in mac (installed via Brew)
    `cd /usr/hdp/current/kafka-broker/`

in linux :<br />
    `cd /opt/kafka/<kafka_version>`

else : wherever you install it

2. Run zookeeper server <br />
    `./bin/zookeeper-server-start libexec/config/zookeeper.properties`

3. Run kafka server <br />
    `./bin/kafka-server-start libexec/config/server.properties`

4. Create topics<br />
    `./bin/kafka-topics --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic colors`

5. Create Producers
   `kafka-console-producer --broker-list localhost:9092 --topic colors`
   
6. Create Consumers
  `kafka-console-consumer --bootstrap-server localhost:9092 --topic colors`

