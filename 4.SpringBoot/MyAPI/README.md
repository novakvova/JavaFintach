mvn clean package

mvn spring-boot:run

http://localhost:8081/swagger-ui/index.html

java -jar target/npr211.jar

mvn clean verify

mvn test

mvn dependency:resolve

mvn clean

java -jar target/npr211.jar --server.port=8081

```
docker build -t npr211-java . 
docker images --all
docker run -it --rm -p 5085:8081 --name npr211_container npr211-java
docker run -d --restart=always --name npr211_container -p 5085:8081 npr211-java
docker run -d --restart=always -v d:/volumes/spring/uploading:/app/uploading --name npr211_container -p 5085:8081 npr211-java
docker run -d --restart=always -v /volumes/spring/uploading:/app/uploading --name npr211_container -p 5085:8081 npr211-java
docker ps -a
docker stop npr211_container
docker rm npr211_container

docker images --all
docker rmi npr211-java

docker login
docker tag npr211-java:latest novakvova/npr211-java:latest
docker push novakvova/npr211-java:latest

docker pull novakvova/npr211-java:latest
docker ps -a
docker run -d --restart=always --name npr211_container -p 5085:8081 novakvova/npr211-java


docker pull novakvova/npr211-java:latest
docker images --all
docker ps -a
docker stop npr211_container
docker rm npr211_container
docker run -d --restart=always --name npr211_container -p 5085:8081 novakvova/npr211-java

---------------/etc/nginx/sites-available/--------------------------

server {
    server_name   slush.itstep.click *.slush.itstep.click;
    location / {
       proxy_pass         http://localhost:5085;
       proxy_http_version 1.1;
       proxy_set_header   Upgrade $http_upgrade;
       proxy_set_header   Connection keep-alive;
       proxy_set_header   Host $host;
       proxy_cache_bypass $http_upgrade;
       proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
       proxy_set_header   X-Forwarded-Proto $scheme;
    }
}

sudo nginx -t
sudo systemctl restart nginx
sudo systemctl status nginx
```
