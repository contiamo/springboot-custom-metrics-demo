# SpringBoot custom metrics demo

This is a absolute minimum demo of how to create a custom metric in SpringBoot with actuator.


```sh
task run
```

Then open
http://localhost:8085/random and reload it several times.

Then open and search for `sleep_latency` in
http://localhost:8085/actuator/prometheus