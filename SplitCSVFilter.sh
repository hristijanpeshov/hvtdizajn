#!/bin/bash

echo "id,type,lat,lon" > bank.csv
echo "id,type,lat,lon" > atm.csv
echo "id,type,lat,lon" > exchange.csv

awk -F ',' '{if($2=="bank") printf("%s\r\n",$0) >> "bank.csv"; else if($2=="atm") printf("%s\r\n",$0) >> "atm.csv"; else printf("%s\r\n",$0) >> "exchange.csv" }'
