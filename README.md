# Spring Boot Monitoring

Up the Docker Compose

```shell
curl -X POST -u elastic:elastic \
  -H "Content-Type: application/json" \
  -d "{\"password\": \"kibana\"}" \
  http://localhost:9200/_security/user/kibana_system/_password
```