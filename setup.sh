#!/bin/bash
echo "You have choosen following standalone xml" "$1"

CONTENT='            <datasources>
                 <datasource jndi-name="java:/swp2" pool-name="swp2">
                    <connection-url>jdbc:h2:./swp2</connection-url>
                    <driver>h2</driver>
                    <pool>
                        <max-pool-size>30</max-pool-size>
                    </pool>
                    <security>
                        <user-name>swp2</user-name>
                        <password>swp2</password>
                    </security>
                </datasource>'

sed '/<\/datasources>/i\'"$CONTENT" $1 --debug
