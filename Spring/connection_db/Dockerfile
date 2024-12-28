# Bắt đầu từ image nền MySQL
FROM mysql:8.0

# Cài đặt các package bổ sung
RUN #apt-get update && apt-get install -y \
#    curl \
#    git \
#    && rm -rf /var/lib/apt/lists/*

# Cấu hình MySQL
ENV MYSQL_ROOT_PASSWORD=Nhan21..
ENV MYSQL_DATABASE=springboot
ENV MYSQL_USER=nhanph
ENV MYSQL_PASSWORD=admin

# Copy các script tùy chỉnh
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Set entrypoint
ENTRYPOINT ["/entrypoint.sh"]
