aws ecr get-login-password --region ap-south-1 | sudo docker login --username AWS --password-stdin 863518452741.dkr.ecr.ap-south-1.amazonaws.com
sudo docker build -t er-backend .
sudo docker tag er-backend:latest 863518452741.dkr.ecr.ap-south-1.amazonaws.com/er-backend:latest
sudo docker push 863518452741.dkr.ecr.ap-south-1.amazonaws.com/er-backend:latest