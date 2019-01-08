while true
do

docker pull tiphedor/fdev-back:latest
docker pull tiphedor/fdev-front:latest
docker-compose up -d --no-deps

echo ""
echo ""

sleep 5

done
