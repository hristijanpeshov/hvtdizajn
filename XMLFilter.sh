#!/bin/bash

if [ $# -ne 1 ]
then
echo "Usage: $0 <open street map xml>"
exit 1
fi

xmlstarlet sel -T -t -m "/osm/node" -m "tag" --if "@k='amenity'" --if "@v='bank'" -v "../@id" -o "," -v "@v" -o "," -v "../@lat" -o "," -v "../@lon" -o "," -m "../tag[@k='name']" -v "@v" -b -n -v "../@id" -o ",atm," -v "../@lat" -v "../@lon" -o "," -m "../tag[@k='name']" -v "@v" -b -n --elif "@v='atm'" -v "../@id" -o "," -v "@v" -o "," -v "../@lat" -o "," -v "../@lon" -o "," --if "../tag[@k='operator']" -m "../tag[@k='operator']" -v "@v" -b --elif "../tag[@k='name']" -m "../tag[@k='name']" -v "@v" -b -b -n --elif "@v='bureau_de_change'" -v "../@id" -o "," -v "@v" -o "," -v "../@lat" -o "," -v "../@lon" -o "," -m "../tag[@k='name']" -v "@v" -b -n -b -b $1

