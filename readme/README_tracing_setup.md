Setting up Tracing

https://chatgpt.com/share/69ad191d-7fa0-800b-bc01-91d9800e20cf

Open this link and find out how we setup, issues we faced n fixed

Commands used : 

  Id CommandLine
  -- -----------
   1 docker run --rm alpine ping -c 3 host.docker.internal
   2 docker run --rm curlimages/curl curl http://host.docker.internal:8080/actuator/health
   3 docker run --rm alpine nslookup host.docker.internal
   6 docker compose config
   7 docker compose ps -a
   8 docker compose logs prometheus
   9 docker compose logs --tail=200
  10 docker compose down --remove-orphans
  11 docker ps -a
  12 dir compose.yaml
  13 docker compose up -d grafana prometheus tempo otel-collector
  14 Remove-Item .\observability\otel\config.yaml -Recurse -Force
  15 New-Item .\observability\otel\config.yaml -ItemType File -Force
  16 docker compose down
  17 docker compose up -d
  18 curl http://localhost:8080/actuator/prometheus
  19 curl.exe http://localhost:8080/actuator/prometheus
  20 curl.exe http://localhost:8080/actuator
  21 curl.exe http://localhost:8080/actuator/env/management.endpoints.web.exposure.include
  22 mvn dependency:tree | Select-String "micrometer-registry-prometheus"
  23 mvn clean package
  24 mvn clean package
  25 curl.exe http://localhost:8080/actuator/conditions
  26 mvn dependency:tree | Select-String "micrometer-registry-prometheus"
  27 curl.exe http://localhost:8080/actuator
  28 curl.exe http://localhost:8080/actuator
  29 mvn dependency:tree | Select-String "micrometer-registry-prometheus"
  30 cd .\demo\
  31 cd .\demo\
  32 mvn dependency:tree | Select-String "micrometer-registry-prometheus"
  
	[INFO] +- io.micrometer:micrometer-registry-prometheus:jar:1.16.1:compile
  33 cd ..
  34 docker compose ps -a
  35 docker compose logs tempo --tail=200
  36 docker compose logs otel-collector --tail=200
  37 curl.exe http://localhost:8080/actuator/health
  38 docker compose ps -a
  39 curl.exe http://localhost:3200/ready
  40 docker compose logs otel-collector --tail=200