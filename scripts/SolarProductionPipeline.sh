#!/usr/bin/env bash

set -euo pipefail

mvn compile exec:java \
  -Dexec.mainClass="ai.alec.deeppow.pipelines.SolarProductionPipeline" \
  -Dexec.args="\
    --runner=DirectRunner \
    --inputFilePath=/Users/alecferguson/Downloads/Plant_1_Generation_Data.csv \
    --outputFilePath=/Users/alecferguson/scratch/dailies \
  "
