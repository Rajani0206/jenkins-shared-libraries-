@Library('my-shared-library') _

pipeline {
    agent { label 'slave' }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        MAVEN_HOME = '/usr/share/maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
				newjenkins.check_out()
            }
		      }
        }

          stage('Set up Java') {
            steps {
                script {
				newjenkins.setup_java()
            		}	
		}
        }
        
          stage('Set up Maven') {
            steps {
                script {
				  newjenkins.setup_maven()
            		}
	    }
        }

       
          stage('Building Project') {
            steps {
                script {
				  newjenkins.build_project()
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
				  newjenkins.run_application()
            		}
		}
        }

          stage('Validating Application') {
            steps {
                script {
				  newjenkins.validate_app()
            		}
		}
        }

          stage('Gracefully Stop Spring Boot App') {
            steps {
                script {
				  newjenkins.graceful_stop()
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
