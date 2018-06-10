set -m

/entrypoint.sh couchbase-server &

sleep 25

# Setup index and memory quota
curl -v -X POST http://127.0.0.1:8091/pools/default -d memoryQuota=300 -d indexMemoryQuota=300

# Setup services
curl -v http://127.0.0.1:8091/node/controller/setupServices -d services=kv%2Cn1ql%2Cindex

# Setup credentials
curl -v http://127.0.0.1:8091/settings/web -d port=8091 -d username=Administrator -d password=password

# Load sample bucket
#curl -v -u Administrator:password -X POST http://127.0.0.1:8091/sampleBuckets/install -d '["travel-sample"]'
curl -X POST -u Administrator:password -d name=default -d flushEnabled=1 -d ramQuotaMB=100 -d authType=none -d replicaNumber=2 -d proxyPort=11315 http://127.0.0.1:8091/pools/default/buckets

fg 1