# 🧩 API RESTful de Gestion de Projets Agile

  <br/>
  
## 🌍 À propos

Cette **API RESTful** aide les **Product Owners**, **Scrum Masters** et **Développeurs** à gérer et suivre efficacement l’avancement des **tâches** et des **User Stories** tout au long du cycle de vie d’un **projet Agile**.

<br/>

## 🎯 Objectifs principaux

- Suivi des **Product Backlogs** et **Sprint Backlogs**.  
- Organisation et priorisation des **User Stories** par **Epics**.  
- Gestion des **Tasks** associées aux **User Stories** et suivi de leur état : *To Do*, *In Progress*, *Done*.  
- Planification et suivi des **Sprints**.

<br/>

## 🧩 Endpoints principaux (Swagger UI)
Voici un aperçu des endpoints exposés par l’API à travers Swagger :
### product-backlog
<p align="center">
  <img src="docs/product-backlog.png" alt="product-backlog" width="600"/>
</p>

### sprintbacklog
<p align="center">
  <img src="docs/sprintbacklog.png" alt="sprintbacklog" width="600"/>
</p>

### epic
<p align="center">
  <img src="docs/epic.png" alt="Dashboard Screenshot" width="600"/>
</p>

### user-story
<p align="center">
  <img src="docs/user-story.png" alt="user-story" width="600"/>
</p>

### task
<p align="center">
  <img src="docs/task.png" alt="task" width="600"/>
</p>

### user
<p align="center">
  <img src="docs/user.png" alt="user" width="600"/>
</p>

<br/>

## 🧱 Architecture et Technologies

| Couche              | Technologie                          | Rôle |
|----------------------|--------------------------------------|------|
| **Framework principal** | Spring Boot                        | Démarrage rapide, gestion des beans |
| **Web Layer**           | Spring Web                         | Expose les endpoints REST |
| **Service Layer**       | Java + Spring                      | Contient la logique métier |
| **Data Layer**          | JPA + Hibernate + MySQL            | Persistance des données |
| **Security Layer**      | Spring Security + JWT              | Authentification & autorisation |
| **Mapping Layer**       | MapStruct / ModelMapper            | Conversion DTO ↔ Entité |
| **Documentation**       | Springdoc OpenAPI (Swagger UI)     | Documentation interactive |
| **Productivité**        | Lombok, DevTools                   | Simplifie et accélère le développement |
| **Tests**               | JUnit, Mockito                     | Tests unitaires et d’intégration |


<br/>
 
## 🧩DTOS

| DTO | Champs |
|------|--------|
| **DescriptionDTO** | `id`, `role`, `besoin`, `raison` |
| **User** | `id`, `name`, `email`, `passwd`, `role`, `version` |
| **UserStoryDTO** | `id`, `titre`, `priorite`, `statut`, `description`, `taskIds`, `sprintBacklogId`, `epicId`, `productBacklogId` |
| **TaskDTO** | `id`, `titre`, `description`, `statut`, `userStoryId`, `sprintBacklogId` |
| **SprintBacklogDTO** | `id`, `nom`, `datedebut`, `datefin`, `userStoryIds`, `taskIds` |
| **ProductBacklogDTO** | `id`, `nom`, `userStoryIds`, `epicIds` |
| **EpicDTO** | `id`, `titre`, `description`, `userStoryIds`, `productBacklogId` |
| **LoginRequest** | `email`, `password` |

<br/>

## 🚀 Démarrage du projet

```bash
git clone https://github.com/zinaakhtat/RestApiProject.git
cd RestApiProject
mvn spring-boot:run
```

## 🤝 Contribution

1. **Fork** le repository

2. **Créer une branche** pour votre fonctionnalité

```bash
git checkout -b feature/AmazingFeature
```

3. **Committer** vos changements

```bash
git commit -m "Add AmazingFeature"
```

4. **Pousser** vers la branche

```bash
git commit -m "Add AmazingFeature"
```

5. **Ouvrir** une Pull Request

<br/>

## 📄 Licence

Projet sous licence **MIT**.  
Voir le fichier [LICENCE](licence) pour plus de détails.

<br/>

