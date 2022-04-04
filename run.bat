docker build -t ecore/challenge:1.0.0 .
docker run -d -p 8080:8080 -e DATABASE_SERVER=jdbc:h2:mem:testdb ecore/challenge:1.0.0