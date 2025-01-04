def gitPushWithBuildNumber(repoUrl, branch, credentialsId, buildNumber) {
    withCredentials([usernamePassword(credentialsId: github-credentials, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
        try {
            git branch: branch, url: repoUrl 
            
            // Create a lightweight tag
            sh "git tag -f v${buildNumber}" 

            // Push the tag to the remote repository
            sh "git push origin v${buildNumber}" 
        } catch (Exception e) {
            // Handle potential errors during git operations
            currentBuild.result = 'FAILED'
            echo "Error pushing tag: ${e.message}"
            throw e 
        }
    }
}
