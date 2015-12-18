#!/usr/bin/env bash

rm t.data
rm u.data
cp o.data t.data
cp o.data u.data

echo "duplicate data for 1000 times"
for i in `seq 1 1000`;
do
    cat t.data >> u.data
done

du -sh *