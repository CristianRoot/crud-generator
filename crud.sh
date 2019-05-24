#!/usr/bin/env bash

for i in "$@"
do
case $i in
    -e=*|--entity=*)
    ENTITY_NAME="${i#*=}"
    shift
    ;;
    -ep=*|--entity-plural=*)
    ENTITY_PLURAL="${i#*=}"
    ENTITY_PLURAL="${ENTITY_PLURAL^}"
    shift
    ;;
    --only-tests)
    ONLY_TESTS=true
    shift
    ;;
    *)
    echo "Invalid argument $i"
    exit 1
    ;;
esac
done

if [[ -z $ENTITY_NAME ]]; then
    echo "Entity name not found"
    exit 1
fi

if [[ -z $ENTITY_PLURAL ]]; then
    echo "Entity plural name not found"
    exit 1
fi

# Parameters
ENDPOINT_NAME=${ENTITY_PLURAL,,}
PACKAGE_NAME=${ENTITY_PLURAL,,}

# Directories
PROJECTDIR=C:/workspace-back/concep-back/src/main/java/com/quantumstudio/concep
PROJECTDIR_TESTS=$PROJECTDIR/integration/${PACKAGE_NAME,,}
WORKDIR=c:/scripts/crud
TEMPLATES_DIR=$WORKDIR/templates
TEST_TEMPLATES_DIR=$WORKDIR/test-templates

# Files
CONTROLLER_FILE=$TEMPLATES_DIR/controller.java
SERVICE_FILE=$TEMPLATES_DIR/service.java
SERVICE_IMPL_FILE=$TEMPLATES_DIR/service-impl.java
REPOSITORY_FILE=$TEMPLATES_DIR/repository.java
MODEL_FILE=$TEMPLATES_DIR/model.java
ENTITY_FILE=$TEMPLATES_DIR/entity.java

# Test files
TEST_CREATE_FILE=$TEST_TEMPLATES_DIR/create.java
TEST_EDIT_FILE=$TEST_TEMPLATES_DIR/edit.java
TEST_GET_FILE=$TEST_TEMPLATES_DIR/get.java
TEST_GET_ALL_FILE=$TEST_TEMPLATES_DIR/get-all.java
TEST_DELETE_FILE=$TEST_TEMPLATES_DIR/delete.java

# Functions
generate_file() {
    cat $1 >> tmp
    sed -i "s/%ENTITY%/$ENTITY_NAME/g" tmp
    sed -i "s/%ENTITY_VAR%/${ENTITY_NAME,}/g" tmp
    sed -i "s/%ENDPOINT_NAME%/${ENDPOINT_NAME}/g" tmp
    cp tmp $2
    rm tmp
    echo "$2 created"
}

generate_test_file() {
    cat $1 >> tmp
    sed -i "s/%ENTITY%/$ENTITY_NAME/g" tmp
    sed -i "s/%ENTITY_VAR%/${ENTITY_NAME,}/g" tmp
    sed -i "s/%ENDPOINT_NAME%/$ENDPOINT_NAME/g" tmp
    sed -i "s/%ENTITY_PLURAL%/$ENTITY_PLURAL/g" tmp
    sed -i "s/%PACKAGE_NAME%/$PACKAGE_NAME/g" tmp
    cp tmp $2
    rm tmp
    echo "$2 created"
}

generate_crud() {
    echo "Generating CRUD..."
    generate_file $CONTROLLER_FILE $PROJECTDIR/controllers/${ENTITY_NAME}Controller.java
    generate_file $SERVICE_FILE $PROJECTDIR/services/${ENTITY_NAME}Service.java
    generate_file $SERVICE_IMPL_FILE $PROJECTDIR/services/impl/${ENTITY_NAME}ServiceImpl.java
    generate_file $REPOSITORY_FILE $PROJECTDIR/repositories/${ENTITY_NAME}Repository.java
    generate_file $MODEL_FILE $PROJECTDIR/models/${ENTITY_NAME}Model.java
    generate_file $ENTITY_FILE $PROJECTDIR/entities/${ENTITY_NAME}.java
    echo "CRUD Done!"
}

generate_tests() {
    echo "Generating TESTS..."

    if [[ ! -e $PROJECTDIR_TESTS ]]; then
        mkdir $PROJECTDIR_TESTS
    fi

    generate_test_file $TEST_GET_ALL_FILE $PROJECTDIR_TESTS/Get${ENTITY_PLURAL}Test.java
    generate_test_file $TEST_CREATE_FILE  $PROJECTDIR_TESTS/Create${ENTITY_NAME}Test.java
    generate_test_file $TEST_EDIT_FILE $PROJECTDIR_TESTS/Edit${ENTITY_NAME}Test.java
    generate_test_file $TEST_DELETE_FILE $PROJECTDIR_TESTS/Delete${ENTITY_NAME}Test.java
    generate_test_file $TEST_GET_FILE $PROJECTDIR_TESTS/Get${ENTITY_NAME}Test.java

    echo "TESTS Done!"
}

# Main program
if [[ $ONLY_TESTS != true ]]; then
    generate_crud
else
    echo "Skipping CRUD..."
fi

generate_tests
