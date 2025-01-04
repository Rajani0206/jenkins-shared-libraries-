def check_out() {
  echo 'Checking out code...'
  checkout scm
}

def setup_java() {
  echo 'Using pre-configured Java...'
  // Assuming Java 17 is already installed
}

def setup_maven() {
  echo 'Using pre-configured Maven...'
  // Assuming Maven is already installed
}

def build_project() {
  echo 'Building project with Maven...'
  sh 'mvn clean package' // Assuming this is the correct build command
}

def upload_artifact(String artifactPath) {
  echo 'Uploading artifact...'
  archiveArtifacts artifacts: artifactPath, allowEmptyArchive: true
}

def run_application() {
  echo 'Running Spring Boot application...'
  sh 'nohup mvn spring-boot:run &'
  sleep(time: 15, unit: 'SECONDS')

  def publicIp = sh(script: "curl -s https://checkip.amazonaws.com", returnStdout: true).trim()
  echo "The application is running and accessible at: http://${publicIp}:8080"
}

def validate_app() {
  echo 'Validating that the app is running...'
  def response = sh(script: 'curl --write-out "%{http_code}" --silent --output /dev/null http://localhost:8080', returnStdout: true).trim()
  if (response == "200") {
    echo 'The app is running successfully!'
  } else {
    echo "The app failed to start. HTTP response code: <span class="math-inline">\{response\}"
error\("The app did not start correctly\!"\)
\}
\}
def graceful\_stop\(\) \{
echo 'Gracefully stopping the Spring Boot application\.\.\.'
sh 'mvn spring\-boot\:stop'
\}
pipeline \{
agent \{ label 'slave' \}
environment \{
JAVA\_HOME \= '/usr/lib/jvm/java\-17\-openjdk\-amd64'
MAVEN\_HOME \= '/usr/share/maven'
PATH \= "</span>{JAVA_HOME}/bin:<span class="math-inline">\{MAVEN\_HOME\}/bin\:</span>{env.PATH}"
  }

  stages {
    stage('Checkout Code') {
      steps {
        script {
          check_out()
        }
      }
    }

    stage('Set up Java') {
      steps {
        script {
          setup_java()
        }
      }
    }

    stage('Set up Maven') {
      steps {
        script {
          setup_maven()
        }
      }
    }

    stage('Building Project') {
      steps {
        script {
          build_project()
        }
      }
    }

  stage('Upload Artifact') {
            steps {
                echo 'Uploading artifact...'
                archiveArtifacts artifacts: 'target/simple-parcel-service-app-1.0-SNAPSHOT.jar', allowEmptyArchive: true
            }
        }

          stage('Run Application') {
            steps {
                script {
				  run_application()
            		}
		}
        }

          stage('Validating Application') {
            steps {
                script {
				  validate_app()
            		}
		}
        }

          stage('Gracefully Stop Spring Boot App') {
            steps {
                script {
				  graceful_stop()
            }
		      }
        }
    }
    post {
        always {
            cleanup()
        }
    }
  }
