# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_js_session',
  :secret      => 'b85a7dce5f359f55c204303dacd17d4c123ab84bf7f8df4b82830e47db3ebd3e8673d8a4690c4147ee6ef7167582d7cf0fde209830c69e043f315589c07f1815'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
