def createGitTag(String credentialsId = 'github-credentials') {
    withCredentials([usernamePassword(credentialsId: github-credentials, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh """
            git config remote.origin.url https://${USERNAME}:${PASSWORD}@github.com/Rajani0206/Parcel-service.git
            git tag -a v${env.BUILD_NUMBER} -m "Build ${env.BUILD_NUMBER}"
            git push origin v${env.BUILD_NUMBER}
        """
    }
}
