select ps.* from partner p, partner_state ps, partner_address_state pas, address a, address_state as1
where p.birth_name = '943name'
and ps.partner_id = p.id
and p.id = pas.partner_id
and a.id = pas.address_id
and a.id = as1.address_id
and '2009-01-01' between ps.state_begin and ps.state_end
and '2009-01-01' between pas.state_begin and pas.state_end
and '2009-01-01' between as1.state_begin and as1