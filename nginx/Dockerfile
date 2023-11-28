FROM nginx:latest

RUN rm -f /etc/nginx/conf.d/default.conf

COPY ./nginx/default.conf /etc/nginx/conf.d/

CMD ["nginx", "-g", "daemon off;"]