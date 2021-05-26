FROM adoptopenjdk/openjdk11:slim AS build
WORKDIR /workspace/app

COPY ./ /workspace/app
RUN target=/root/.gradle ./gradlew build
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM adoptopenjdk/openjdk11:jre-11.0.11_9
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.flixbus.business.BusinessApplicationKt"]
