FROM quay.io/centos/centos:stream9
ENV container docker

# Install basic utilities

RUN dnf -y install epel-release
RUN yum clean all
RUN yum -y install wget
RUN yum -y install git
RUN yum -y install java-17-openjdk

RUN yum -y install iproute
RUN yum -y install telnet
RUN yum -y install iputils
RUN yum -y install maven
RUN yum -y install docker
RUN yum -y install openssh-clients
RUN wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
RUN rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
RUN yum -y install jenkins

RUN ln -sf /usr/share/zoneinfo/Asia/Kuala_Lumpur /etc/localtime && \
    echo "Asia/Kuala_Lumpur" > /etc/timezone
	
# Optional: Create a non-root user
RUN adduser developer && echo "developer:pass" | chpasswd

# Set working directory
WORKDIR /workspace

# Default command

#CMD ["/bin/bash"]

CMD ["jenkins"]