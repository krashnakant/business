FROM node:14-alpine as build

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

RUN npm install

RUN npm run build

# for production used image version should be used instead of latest
FROM nginx:latest
COPY --from=build /usr/local/app/dist/agency-portal /usr/share/nginx/html

EXPOSE 80
CMD ["nginx","-g","daemon off;"]
