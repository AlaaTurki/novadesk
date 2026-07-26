INSERT INTO roles (
    id,
    name,
    created_at,
    updated_at
)
VALUES
(
    gen_random_uuid(),
    'USER',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    gen_random_uuid(),
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
)
ON CONFLICT (name) DO NOTHING;