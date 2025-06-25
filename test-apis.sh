#!/bin/bash

api_base_url_dev=http://localhost:8082

for i in {1..10}
  do
    printf "Iteration # %s...\n" "${i}"
    curl "${api_base_url_dev}/books"
    curl "${api_base_url_dev}/books/external"
#    curl "${api_base_url_dev}/fast"
#    printf "\n"
#    curl "${api_base_url_dev}/slow"
#    printf "\n"
#    curl "${api_base_url_dev}/super-slow" &
#    printf "\n\n"
done