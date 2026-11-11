# helio-demo-api

A small Spring Boot API (`GET /` and `GET /actuator/health`, port 8080), delivered to ECS Fargate by pipeline.

`.github/workflows/helio-delivery.yml` runs on every push to `main`:

1. **Build**: tests, then the image is built and pushed by digest. AWS access is by GitHub's identity token only; no stored cloud keys.
2. **Scan**: the pushed image is scanned; critical findings with a fix stop the run.
3. **Deploy**: waits for approval of the `production` environment, then asks Helio to roll out that exact digest. Helio accepts the request only from this repository, this branch and this workflow file.
4. **Verify endpoint**: calls the running service and fails the run if it is not healthy.
