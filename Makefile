AWS_ACCESS_KEY_ID := ''
AWS_SECRET_ACCESS_KEY := ''
AWS_DEFAULT_REGION := 'eu-west-3'
SECURITY_GROUPS := 'snowcamp'
IMAGE_ID := 'ami-4262d53f' # ubuntu 14.04 LTS amazon official
NUMBER_INSTANCES := 1
INSTANCE_TYPE := 't2.micro' # recommanded : m5.xlarge
USER_DATA_FILE := 'aws/user-data.yml'
MAPPING_FILE := 'aws/mapping.json'

#       _            _
#      | |          | |
#    __| | ___   ___| | _____ _ __
#   / _` |/ _ \ / __| |/ / _ \ '__|
#  | (_| | (_) | (__|   <  __/ |
#   \__,_|\___/ \___|_|\_\___|_|
#
.PHONY: build
build:
	@docker-compose build

.PHONY: start
start: build
	@docker-compose up -d

.PHONY: stop
stop:
	@docker-compose stop

.PHONY: remove
remove: stop
	@docker-compose rm -f

#    __ ___      _____
#   / _` \ \ /\ / / __|
#  | (_| |\ V  V /\__ \
#   \__,_| \_/\_/ |___/
#
.PHONY: aws-run-instances
aws-run-instances:
	@docker run --rm -t -e "AWS_ACCESS_KEY_ID=$(AWS_ACCESS_KEY_ID)" -e "AWS_SECRET_ACCESS_KEY=$(AWS_SECRET_ACCESS_KEY)" -e "AWS_DEFAULT_REGION=$(AWS_DEFAULT_REGION)" -v "$(PWD):/project" mesosphere/aws-cli ec2 run-instances --image-id $(IMAGE_ID) --count $(NUMBER_INSTANCES) --instance-type $(INSTANCE_TYPE) --security-groups $(SECURITY_GROUPS) --user-data file://$(USER_DATA_FILE) --block-device-mappings file://$(MAPPING_FILE)
