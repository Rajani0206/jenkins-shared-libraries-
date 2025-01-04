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

        stages {
          stage('Set up Java') {
            steps {
                script {
				pipeline.setup_java()
            }
		      }
        }
        stages {
          stage('Set up Maven') {
            steps {
                script {
				  pipeline.setup_maven()
            }
		      }
        }

        stages {
          stage('Building Project') {
            steps {
                script {
				  pipeline.build_project()
            }
		      }
        }

        stages {
          stage('Upload Artifact') {
            steps {
                script {
				  pipeline.upload_artifact()
            }
		      }
        }
       stages {
          stage('Run Application') {
            steps {
                script {
				  pipeline.run_application()
            }
		      }
        }

        stages {
          stage('Validating Application') {
            steps {
                script {
				  pipeline.validate_app()
            }
		      }
        }

       stages {
          stage('Gracefully Stop Spring Boot App') {
            steps {
                script {
				  pipeline.upload_artifact()
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
}
