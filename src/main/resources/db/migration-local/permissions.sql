-- Permissions
insert into permissions (code, description)
values
    ('USER_READ', 'Ler usuários'),
    ('USER_WRITE', 'Criar/alterar usuários'),
    ('ROLE_READ', 'Ler papéis'),
    ('ROLE_WRITE', 'Criar/alterar papéis')
    on conflict (code) do nothing;

-- Rules
insert into rules (code, description, expression)
values
    ('ONLY_OWN_RESOURCE', 'Acesso apenas ao próprio recurso', 'resource.ownerId == principal.id'),
    ('TENANT_MATCH', 'Acesso apenas ao mesmo tenant', 'resource.tenantId == principal.tenantId')
    on conflict (code) do nothing;

-- Roles
insert into roles (code, description)
values
    ('ADMIN', 'Acesso administrativo'),
    ('OPERATOR', 'Acesso operacional')
    on conflict (code) do nothing;

-- Role -> Permissions
insert into roles_permissions (role_id, permission_id)
select r.id, p.id
from roles r
         join permissions p on p.code in ('USER_READ','USER_WRITE','ROLE_READ','ROLE_WRITE')
where r.code = 'ADMIN'
    on conflict (role_id, permission_id) do nothing;

insert into roles_permissions (role_id, permission_id)
select r.id, p.id
from roles r
         join permissions p on p.code in ('USER_READ','ROLE_READ')
where r.code = 'OPERATOR'
    on conflict (role_id, permission_id) do nothing;

-- Role -> Rules
insert into roles_rules (role_id, rule_id)
select r.id, ru.id
from roles r
         join rules ru on ru.code in ('TENANT_MATCH')
where r.code = 'OPERATOR'
    on conflict (role_id, rule_id) do nothing;