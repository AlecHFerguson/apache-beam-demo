#!/usr/bin/env bash

set -euo pipefail

DATA_BUCKET=gs://deeppow-data-bucket

ARGS=(
    solarInputFilePath=${DATA_BUCKET}/solar-data/SolarGenerationData.avro
    weatherInputFilePath=${DATA_BUCKET}/solar-data/WeatherData.avro
    outputFilePath=${DATA_BUCKET}/outputs/implied-production
    maxNumWorkers=10
)
JOINED=$(IFS=,; printf '%s' "${ARGS[*]}")

gcloud dataflow jobs run SolarProductionPipeline \
    --gcs-location gs://deeppow-dataflow-templates/templates/SolarProductionPipeline \
    --region=us-central1 \
    --parameters="${JOINED}"
