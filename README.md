# SiGoApp Backend

## Descripción

El backend de SiGoApp está construido en Java utilizando Spring Boot. Proporciona funcionalidades esenciales para la gestión de un punto de venta, incluyendo la gestión de inventarios, ventas, control de cajas, y administración de usuarios.

## Características Principales

1. **Gestión de Inventarios:**
   - Registro y seguimiento de productos en inventario.
   - Actualización automática de la cantidad de productos después de cada venta.

2. **Ventas Automatizadas:**
   - Sistema automatizado que realiza la baja de la cantidad en inventario al completar una venta.
   - Contribuye al control preciso del stock.

3. **Control de Cajas:**
   - Registro y seguimiento del dinero en caja.
   - Información detallada sobre el dinero vendido y el dinero actual en caja.
   - Posibilidad de realizar retiros de efectivo para mantener un control preciso de las cajas.

4. **Administración de Usuarios:**
   - Sistema de autenticación para la creación y gestión de usuarios.
   - Permisos y roles asignados a los usuarios para garantizar la seguridad y el acceso adecuado.

## Tecnologías Utilizadas

- **Java:** Lenguaje de programación principal.
- **Spring Boot:** Framework para el desarrollo de aplicaciones Java basadas en Spring.
- **Base de Datos:** MySql y JPARepository
