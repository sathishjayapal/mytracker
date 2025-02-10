FROM ubuntu:latest

LABEL authors="skminfotech"

ENTRYPOINT ["top", "-b"]

COPY requirements.txt .

# Copy resources
WORKDIR /
COPY wait-for-it.sh wait-for-it.sh

CMD ./wait-for-it.sh -s -t 30 $ZOOKEEPER_SERVER -- ./wait-for-it.sh -s -t 30 $KAFKA_SERVER
