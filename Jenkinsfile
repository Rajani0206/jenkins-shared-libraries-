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
				pipeline.check_out()
            }
		      }
        }

          stage('Set up Java') {
            steps {
                script {
				pipeline.setup_java()
            		}	
		}
        }
        
          stage('Set up Maven') {
            steps {
                script {
				  pipeline.setup_maven()
            		}
	    }
        }

       
          stage('Building Project') {
            steps {
                script {
				  pipeline.build_project()
       			}
		}
        }

          sstage('Upload Artifact') {
            steps {
                echo 'Uploading artifact...'
                archiveArtifacts artifacts: 'target/petclinic-0.0.1-SNAPSHOT.jar', allowEmptyArchive: true
            }
        }

          stage('Run Application') {
            steps {
                script {
				  pipeline.run_application()
            		}
		}
        }

          stage('Validating Application') {
            steps {
                script {
				  pipeline.validate_app()
            		}
		}
        }

          stage('Gracefully Stop Spring Boot App') {
            steps {
                script {
				  pipeline.graceful_stop()
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

