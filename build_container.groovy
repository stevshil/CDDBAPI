stages {
  stage('Build DB Image') {
    when {
      anyOf{
        changeset "Build_Containers/Dockerfile-mysql"
        triggeredBy cause: "UserIdCause"
      }
    }
    steps {
      container('kaniko') {
        sh '''echo '{ "credsStore": "ecr-login" }' > /kaniko/.docker/config.json
        /kaniko/executor -f `pwd`/Build_Containers/Dockerfile-mysql -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:cddb-latest'''
      }
    }
  }
  stage('Build App Image') {
    when {
      anyOf{
        changeset "Build_Containers/Dockerfile-cdapi"
        triggeredBy cause: "UserIdCause"
      }
    }
    steps {
      container('kaniko') {
        sh '''echo '{ "credsStore": "ecr-login" }' > /kaniko/.docker/config.json
        /kaniko/executor -f `pwd`/Build_Containers/Dockerfile-cdapi -c `pwd` --insecure --skip-tls-verify --cache=false --destination=${ECR_REPO}:cdapi-latest'''
      }
    }
  }
}
