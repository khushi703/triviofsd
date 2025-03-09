import "../css/header.css"

export default function Header() {
  return (
    <header className="app-header">
      <div className="header-left">
        <h1 className="header-title">Trivio</h1>
      </div>
      <div className="header-right">
        <div className="header-search">
          <input type="search" placeholder="Search..." className="search-input" />
        </div>
        <div className="header-actions">
          <button className="header-button">
          </button>
          <div className="user-profile">
            <img
              src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/Screenshot%202025-01-29%20182701-WhnWALtiOcw475OJIPXBkymVdC8zim.png"
              alt="Profile"
              className="profile-image"
            />
          </div>
        </div>
      </div>
    </header>
  )
}

