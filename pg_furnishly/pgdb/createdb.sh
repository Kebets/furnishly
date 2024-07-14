#!/bin/sh

psql << EOF

CREATE DATABASE $POSTGRES_DB OWNER $POSTGRES_USER;

EOF

pg_restore -d $DB_NAME /docker-entrypoint-initdb.d/$DB_NAME.dump
