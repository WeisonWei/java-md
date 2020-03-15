#!/usr/bin/env bash
skipDocker="-Ddockerfile.skip=true"
skipTest="-Dmaven.test.skip=true"
skipDoc="-Dmaven.javadoc.skip=true"

enable_params="repo"
disable_params="!aliyun-nexus-repo"
params="-DskipTests -P ${enable_params},${disable_params}"

./mvnw -B release:clean release:prepare -Darguments="${params1}" release:perform -Darguments="${params2}"
# mvn -B release:clean release:prepare release:perform