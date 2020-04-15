#!/usr/bin/env bash
#target=`docker -H ${host} service ls -f name="${profile}_${service}" -q`
target=`docker -H docker.meixiu.mobi service ls |grep 'ai-app-dev_studentService'`
echo ${target}
if [[ ! -z ${target} ]]; then
  #docker -H ${host} service update ${profile}_${service} --image ${image}
  echo 'exists'
else
  echo "not find service "
fi