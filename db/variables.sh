#!/bin/bash

if [ -f .env ]; then
    export $(cat .env | xargs)
fi

if [ -z "$DB_USER" ] || [ -z "$DB_PASSWORD" ]; then
    echo "As variáveis DB_USER e DB_PASSWORD devem ser definidas."
    exit 1
fi
