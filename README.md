# MyTracker

A comprehensive microservices ecosystem for tracking running activities from multiple sources including Garmin, Strava, and meditation practices. Built with event-driven architecture using Apache Kafka for real-time data streaming and synchronization.

## 🏗️ Architecture Overview

MyTracker follows a microservices architecture with Kafka-based event streaming, enabling real-time synchronization across multiple tracking services.

### Service Modules

- **trackgarmin/** - Garmin Connect integration and activity synchronization
- **trackstrava/** - Strava API integration for activity import
- **trackmeditation/** - Meditation session tracking and analytics
- **sathishaccount-query/** - Account query service for user management
- **sathishruns-common/** - Shared utilities, DTOs, and common code
- **garmindatainitializer/** - Garmin database initialization and seed data
- **sathishrunsinitfordb/** - Database schema initialization service

## 🛠️ Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot (Multi-module Maven project)
- **Message Broker:** Apache Kafka
- **Build Tool:** Maven with Spotless code formatter
- **Database:** PostgreSQL (per-service databases)
- **Container:** Docker & Docker Compose
- **Code Quality:** Spotless for consistent formatting

## ✨ Key Features

- **Multi-source Activity Tracking** - Aggregate activities from Garmin and Strava
- **Real-time Event Streaming** - Kafka-based event processing
- **Meditation Tracking** - Track and analyze meditation sessions
- **Distributed Architecture** - Independent, scalable microservices
- **Code Quality** - Automated code formatting with Spotless
- **Containerized Deployment** - Full Docker support

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.8+
- Docker & Docker Compose
- Apache Kafka (or use Docker Compose)
- PostgreSQL (or use Docker Compose)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd mytracker
```

### 2. Configure Environment

Copy and configure the environment file:

```bash
cp .env.example .env
# Edit .env with your configuration
```

### 3. Start Infrastructure Services

Start Kafka, Zookeeper, and PostgreSQL:

```bash
docker-compose up -d
```

### 4. Build All Modules

```bash
mvn clean install
```

### 5. Run Individual Services

```bash
# Start Garmin tracking service
cd trackgarmin
mvn spring-boot:run

# Start Strava tracking service
cd ../trackstrava
mvn spring-boot:run

# Start meditation tracking service
cd ../trackmeditation
mvn spring-boot:run
```

## 📁 Project Structure

```
mytracker/
├── trackgarmin/             # Garmin integration service
├── trackstrava/             # Strava integration service
├── trackmeditation/         # Meditation tracking service
├── sathishaccount-query/    # Account query service
├── sathishruns-common/      # Shared utilities and DTOs
├── garmindatainitializer/   # Garmin data initialization
├── sathishrunsinitfordb/    # Database initialization
├── pom.xml                  # Parent Maven configuration
├── .env                     # Environment configuration
├── docker-compose.yml       # Docker services
└── HabitApp.http           # API testing requests
```

## ⚙️ Configuration

### Environment Variables

Key configuration in `.env`:

```properties
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
DATABASE_URL=jdbc:postgresql://localhost:5432/mytracker
GARMIN_API_KEY=your_garmin_key
STRAVA_CLIENT_ID=your_strava_id
STRAVA_CLIENT_SECRET=your_strava_secret
```

### Database Configuration

Each service has its own database schema. Run initialization services:

```bash
cd sathishrunsinitfordb
mvn spring-boot:run
```

## 📡 Kafka Setup & Management

### Installation (macOS)

```bash
brew install kafka
```

### Starting Kafka

```bash
# Start Zookeeper
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

# Start Kafka Server
kafka-server-start /usr/local/etc/kafka/server.properties
```

### Useful Kafka Commands

**Create Topic:**
```bash
kafka-topics --create --bootstrap-server localhost:9092 \
  --replication-factor 1 --partitions 1 --topic activity-events
```

**List Topics:**
```bash
kafka-topics --list --bootstrap-server localhost:9092
```

**Describe Topic:**
```bash
kafka-topics --describe --bootstrap-server localhost:9092 --topic activity-events
```

**Produce Messages (for testing):**
```bash
kafka-console-producer --broker-list localhost:9092 --topic activity-events
```

**Consume Messages (for testing):**
```bash
kafka-console-consumer --bootstrap-server localhost:9092 \
  --topic activity-events --from-beginning
```

## 🔌 API Integration

### Garmin Connect Integration

The `trackgarmin` service uses OAuth to connect to Garmin Connect:

1. Register your application at Garmin Developer Portal
2. Configure OAuth credentials in `.env`
3. Service will automatically sync activities

### Strava Integration

The `trackstrava` service integrates with Strava API:

1. Create application at Strava API Settings
2. Set OAuth credentials in `.env`
3. Authenticate users through OAuth flow

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Service Tests

```bash
cd trackgarmin
mvn test
```

### API Testing

Use the `HabitApp.http` file with IntelliJ HTTP Client or similar tools for API testing.

## 🐳 Docker Deployment

### Build Images

```bash
mvn clean package
docker-compose build
```

### Start All Services

```bash
docker-compose up -d
```

### View Logs

```bash
docker-compose logs -f trackgarmin
```

## 🔧 Code Quality

This project uses Spotless for code formatting.

### Format Code

```bash
mvn spotless:apply
```

### Check Formatting

```bash
mvn spotless:check
```

## 📊 Monitoring & Health Checks

Each service exposes Spring Boot Actuator endpoints:

- Health: `http://localhost:{port}/actuator/health`
- Metrics: `http://localhost:{port}/actuator/metrics`
- Info: `http://localhost:{port}/actuator/info`

## 🐛 Troubleshooting

### Docker Issues on Intel Mac

If experiencing Docker Desktop issues on Intel Mac:

1. Open Activity Monitor
2. Search for "docker" and Force Quit all Docker processes
3. Delete folder: `~/Library/Group Containers/group.com.docker`
4. Restart Docker Desktop

### Kafka Connection Issues

- Ensure Zookeeper is running before Kafka
- Check `KAFKA_BOOTSTRAP_SERVERS` configuration
- Verify network connectivity to Kafka brokers

### Database Connection Issues

- Verify PostgreSQL is running
- Check database credentials in `.env`
- Ensure database schemas are initialized

## 🔄 Event Flow

1. **Activity Creation** - User creates activity via Garmin/Strava
2. **Event Publishing** - Service publishes event to Kafka topic
3. **Event Processing** - Subscriber services process events
4. **Data Persistence** - Activity stored in service database
5. **Notification** - Optional notifications sent to users

## 🌐 Service Communication

Services communicate through:

- **Kafka Events** - Asynchronous event-driven communication
- **REST APIs** - Synchronous service-to-service calls
- **Shared Database** - Common module for DTOs and utilities

## 📈 Future Enhancements

- [ ] GraphQL API gateway
- [ ] Real-time dashboard
- [ ] Mobile app integration
- [ ] Advanced analytics and insights
- [ ] Social features (following, challenges)
- [ ] Apple Health integration

## 🤝 Contributing

1. Create a feature branch
2. Make changes and ensure code formatting (`mvn spotless:apply`)
3. Run tests (`mvn test`)
4. Submit a pull request

## 📝 License

[Add your license here]

## 👤 Author

Sathish Jayapal

---

Last Updated: February 2026
