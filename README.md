# Spring Boot Monitoring with Tools

Up the Docker Compose
```shell
docker compose up -d
```

```shell
curl -X POST -u elastic:elastic \
  -H "Content-Type: application/json" \
  -d "{\"password\": \"kibana\"}" \
  http://localhost:9200/_security/user/kibana_system/_password
```