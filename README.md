# local-redis
Local Redis Server for Test 
Do not required install redis server

## build
mvn clean package

## run
java -jar local-redis-1.0.0.jar [expose port] [max memory]
(default expose port = 16379, default max memory = 128M)

## stop
ctrl + c
